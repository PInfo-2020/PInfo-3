#!/bin/sh
set -e

echo "here2"


export KONG_NGINX_DAEMON=off
#ls -lr /usr/local/kong/ssl
#whoami
#chmod -R 777 /usr/local
#echo "done"
#whoami
#chmod -R 777 /usr/local/kong

echo "here1"

has_transparent() {
  echo "$1" | grep -E "[^\s,]+\s+transparent\b" >/dev/null
}

#chmod 777 /usr/
#chmod 777 /usr/local/kong/ssl/certs
#chown -R kong /usr/
if [[ "$1" == "kong" ]]; then
  PREFIX=${KONG_PREFIX:=/usr/local/kong}

  if [[ "$2" == "docker-start" ]]; then
    shift 2
    echo "chaning permission"
    whoami
    #chmod -R a+rwx /usr/local/kong
    kong prepare -p "$PREFIX" "$@"
    #chmod -R 777 /usr/local/kong/ssl/certs   
    echo "before"
    chown -R kong "$PREFIX"
    echo "after"
    #chmod -R 777 /usr/local/kong/ssl/certs
    #chown -R kong /usr/local/kong/ssl/certs
    # workaround for https://github.com/moby/moby/issues/31243
    chmod o+w /proc/self/fd/1
    chmod o+w /proc/self/fd/2

    if [ ! -z ${SET_CAP_NET_RAW} ] \
        || has_transparent "$KONG_STREAM_LISTEN" \
        || has_transparent "$KONG_PROXY_LISTEN" \
        || has_transparent "$KONG_ADMIN_LISTEN";
    then
      setcap cap_net_raw=+ep /usr/local/openresty/nginx/sbin/nginx
    fi

    exec su-exec root /usr/local/openresty/nginx/sbin/nginx \
      -p "$PREFIX" \
      -c nginx.conf
  fi
fi

exec "$@"
