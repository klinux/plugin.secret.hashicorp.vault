# Vars
BUILD_DIR="build"
PLUGIN="data/plugins"

.PHONY: build

build :
	./gradlew clean test assemble

.PHONY: copy

copy: build/libs/*.jar
	cp $< ${PLUGIN}

.PHONY: clean

clean:
	rm -rf ${BUILD_DIR} && \
	rm -f ${PLUGIN}/*.jar

all: clean build copy