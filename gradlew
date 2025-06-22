#!/usr/bin/env sh

##############################################################################
# Gradle start-up script for UN*X                                           #
##############################################################################

DIR="$(cd "$(dirname "$0")" && pwd)"
exec java -jar "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
