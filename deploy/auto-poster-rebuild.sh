#!/usr/bin/env bash
# Przebudowa i restart auto-poster-backend. Wywoływany z update.sh przez sudo —
# user deploy nie należy do grupy docker, dostaje TYLKO tę jedną operację.
#
# Instalacja (raz, jako root):
#   cp auto-poster-rebuild.sh /usr/local/bin/auto-poster-rebuild.sh
#   chown root:root /usr/local/bin/auto-poster-rebuild.sh && chmod 755 /usr/local/bin/auto-poster-rebuild.sh
#   echo 'deploy ALL=(root) NOPASSWD: /usr/local/bin/auto-poster-rebuild.sh' > /etc/sudoers.d/auto-poster-rebuild
#   chmod 440 /etc/sudoers.d/auto-poster-rebuild
set -euo pipefail
cd /srv/apps/auto-poster-backend
docker compose build api
docker compose up -d
