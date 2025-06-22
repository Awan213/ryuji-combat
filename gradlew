#!/usr/bin/env sh

##############################################################################
# Gradle start-up script for UN*X                                           #
##############################################################################

# Resolve directory
DIR="$(cd "$(dirname "$0")" && pwd)"

# Jalankan wrapper dengan Java
exec java -jar "$DIR/gradle/wrapper/gradle-wrapper.jar" "$@"
