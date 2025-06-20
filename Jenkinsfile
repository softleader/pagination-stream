#!/usr/bin/env groovy

pipeline {
  agent {
    kubernetes {
      cloud 'SLKE'
      workspaceVolume persistentVolumeClaimWorkspaceVolume(claimName: 'workspace-claim', readOnly: false)
      defaultContainer 'maven-java11'
      yaml """
kind: Pod
spec:
  # All containers should have the same UID
  securityContext:
    runAsUser: 0
  containers:
  - name: maven-java11
    image: harbor.softleader.com.tw/library/maven:3-eclipse-temurin-11
    imagePullPolicy: Always
    command: ['cat']
    tty: true
    resources:
      limits:
        memory: "2.5Gi"
        cpu: "2"
    volumeMounts:
    - name: m2
      mountPath: /root/.m2
    - name: dockersock
      mountPath: /var/run/docker.sock
  - name: maven-java17
    image: harbor.softleader.com.tw/library/maven:3-eclipse-temurin-17
    imagePullPolicy: Always
    command: ['cat']
    tty: true
    resources:
      limits:
        memory: "2.5Gi"
        cpu: "2"
    volumeMounts:
    - name: m2
      mountPath: /root/.m2
    - name: dockersock
      mountPath: /var/run/docker.sock
  - name: git
    image: alpine/git:v2.32.0
    command: ['cat']
    tty: true
    resources:
      limits:
        memory: "100Mi"
        cpu: "100m"
  volumes:
  - name: m2
    persistentVolumeClaim:
      claimName: m2-claim
  - name: dockersock
    hostPath:
      path: /var/run/docker.sock
"""
    }
  }

  environment {
    MAVEN_OPTS="-Xmx2048m -XX:MaxMetaspaceSize=128m"
  }

  stages {

    stage('Confirm Env') {
      steps {
        container('git') {
          script {
            env.LAST_COMMIT_AUTHOR_NAME = sh(
              script: 'git --no-pager show -s --format=%an',
              returnStdout: true
            ).trim()
            env.LAST_COMMIT_AUTHOR_EMAIL = sh(
              script: 'git --no-pager show -s --format=%ae',
              returnStdout: true
            ).trim()
            env.LAST_COMMIT_TIME = sh(
              script: 'git --no-pager show -s --date=format:"%Y/%m/%d %T" --format=%ad',
              returnStdout: true
            ).trim()
          }
        }
        sh 'printenv'
        sh 'java -version'
        sh 'mvn --version'
        echo "${params}"
      }
    }

    stage('Compile and Style Check') {
      steps {
        sh "make compile"
        container('git') {
          sh '[ ! -z "$(git status -s)" ] && exit 1 || echo "Good to go!"'
        }
      }
    }

    stage('Style Check (Spring 2, Java 11)') {
      steps {
        sh "make test"
      }
    }

    stage('Unit Testing (Spring 3, Java 17)') {
      steps {
        container('maven-java17') {
          sh "make test JAVA=17 SPRING=3.5.0"
        }
      }
      post {
        always {
          junit "**/target/surefire-reports/**/*.xml"
        }
      }
    }
  }

  post {
    failure {
      script {
        if (env.BRANCH_NAME == 'main'
            // 若短時間太密集的 push, 之前的 job 會被 jenkins 中斷, 這樣就可能會就連第一步都還沒執行的狀況, 但也算是失敗
            // 然而取得 git 資訊就在第一步, 所以至少要第一步都有執行完才發佈 slack 吧
            && env.LAST_COMMIT_AUTHOR_NAME && env.LAST_COMMIT_AUTHOR_EMAIL && env.LAST_COMMIT_TIME) {
          slackSend(
            color: "danger",
            channel: "dept-rd",
            message: "Attention @here, The pipeline <$BUILD_URL|*${env.JOB_NAME} #${env.BUILD_NUMBER}*> has failed! :omg:\n>Last commit by ${env.LAST_COMMIT_AUTHOR_NAME} (${env.LAST_COMMIT_AUTHOR_EMAIL}) @ ${env.LAST_COMMIT_TIME}"
          )
        }
      }
    }
  }
}
