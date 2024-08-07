# RELEASE defines the release-version fob the bundle.
VERSION ?=

# Functions
define java_version
$(if $(filter-out "",$(JAVA)),-D'java.version=$(JAVA)',)
endef

define spring_version
$(if $(filter-out "",$(SPRING)),-D'spring-boot.version=$(SPRING)',)
endef

##@ General

help: ## Display this help.
	@awk 'BEGIN {FS = ":.*##"; printf "\nUsage:\n  make \033[36m<target>\033[0m\n"} /^[a-zA-Z_0-9-]+:.*?##/ { printf "  \033[36m%-15s\033[0m %s\n", $$1, $$2 } /^##@/ { printf "\n\033[1m%s\033[0m\n", substr($$0, 5) } ' $(MAKEFILE_LIST)

##@ Develop

format: ## Format the source code.
	mvn validate

clean: ## Remove files generated at build-time.
	mvn clean -e

compile: clean  ## Clean and compile the source code.
	mvn compile -e

test: clean ## Clean and test the compiled code.
	mvn test -e $(call java_version) $(call spring_version)

install: clean ## Install project to local repository w/o unit testing.
	mvn install -e -DskipTests -Prelease

bump-deps: ## Bump dependencies to the latest version (excluding Spring).
	mvn versions:update-properties -DexcludeProperties=spring-boot.version
	mvn versions:commit

##@ Delivery

version: ## Get current project version
	@mvn help:evaluate -Dexpression=project.version -DforceStdout -q

new-version: ## Update version.
ifeq ($(strip $(VERSION)),)
	$(error VERSION is required)
endif
	mvn versions:set -DnewVersion=$(VERSION)
	mvn versions:commit

release: clean ## Pack w/o unit testing, and deploy to remote repository.
	mvn deploy -e -DskipTests -Prelease
