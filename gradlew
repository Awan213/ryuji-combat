#!/usr/bin/env sh
##############################################################################
## Gradle start script for UN*X
##############################################################################
APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

DIRNAME=`dirname "$0"`
# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

warn () {
    echo "$*"
}

die () {
    echo
    echo "$*"
    echo
    exit 1
}

# Attempt to set MAX_FD_LIMIT
MAX_FD_LIMIT=`ulimit -H -n`
if [ $? -eq 0 ]; then
    if [ "$MAX_FD_LIMIT" = "unlimited" ]; then
        ulimit -n 65536
    else
        ulimit -n $MAX_FD_LIMIT
    fi
    if [ $? -ne 0 ]; then
        warn "Could not set maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
else
    warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
fi

# Locate java command
if [ -n "$JAVA_HOME" ] ; then
    if [ -x "$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
        JAVA_CMD="$JAVA_HOME/jre/sh/java"
    else
        JAVA_CMD="$JAVA_HOME/bin/java"
    fi
    if [ ! -x "$JAVA_CMD" ] ; then
        die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME
Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
else
    JAVA_CMD="java"
    which java >/dev/null 2>&1 || die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
fi

# Execute Gradle
exec "$JAVA_CMD" -cp "${APP_HOME}/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
