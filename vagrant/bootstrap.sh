#!/usr/bin/env bash

echo "Updating apt-get..."
apt-get update > /dev/null 2>&1

# MariaDB
which mysql > /dev/null 2>&1
if [ $? -ne 0 ]; then
  echo "Installing MariaDB..."
  DEBIAN_FRONTEND=noninteractive apt-get install -q -y mariadb-server > /dev/null 2>&1

  cat > /etc/mysql/my.cnf << EOF
[client]
port                    = 3306
socket                  = /var/run/mysqld/mysqld.sock
default-character-set   = utf8

[mysqld_safe]
socket                  = /var/run/mysqld/mysqld.sock
nice                    = 0

[mysqld]
user                    = mysql
pid-file                = /var/run/mysqld/mysqld.pid
socket                  = /var/run/mysqld/mysqld.sock
port                    = 3306
basedir                 = /usr
datadir                 = /var/lib/mysql
tmpdir                  = /tmp
lc-messages-dir         = /usr/share/mysql
skip-external-locking
bind-address            = 0.0.0.0
key_buffer              = 16M
max_allowed_packet      = 16M
thread_stack            = 192K
thread_cache_size       = 8
myisam-recover          = BACKUP
query_cache_limit       = 1M
query_cache_size        = 16M
log_error               = /var/log/mysql/error.log
expire_logs_days        = 10
max_binlog_size         = 100M
character-set-server    = utf8

[mysqldump]
quick
quote-names
max_allowed_packet      = 16M

[mysql]
default-character-set   = utf8

[isamchk]
key_buffer              = 16M
!includedir /etc/mysql/conf.d/
EOF

  mysql -uroot -e "CREATE DATABASE IF NOT EXISTS test CHARACTER SET utf8;
GRANT ALL ON test.* to sa;
FLUSH PRIVILEGES;
SET PASSWORD FOR sa=PASSWORD('password');"
  service mysql restart
fi
