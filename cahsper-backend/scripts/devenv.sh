#!/bin/sh

export CAHSPER_DB_DATASOURCE_URL="jdbc:mariadb://127.0.0.1/cahsper?useUnicode=true&characterEncoding=utf8mb4"
export CAHSPER_DB_DATASOURCE_USER="root"
export CAHSPER_DB_DATASOURCE_PASSWORD="pass"
export CAHSPER_DB_CONNECTION_TIMEOUT=30000
export CAHSPER_DB_MAXIMUM_POOLSIZE=6