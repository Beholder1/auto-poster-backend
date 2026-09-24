#!/usr/bin/env bash
# Deploy auto-poster-backend: pull repo i przebudowa kontenera API
# (przez sudo auto-poster-rebuild.sh — instalacja opisana w tamtym pliku).
# Kopiowany na serwer jako /srv/apps/auto-poster/update.sh.
set -euo pipefail
cd "$(dirname "$0")/auto-poster-backend"

git fetch origin
# porownanie z upstreamem biezacej galezi (odporne na zmiane main/master)
if [ "$(git rev-parse HEAD)" = "$(git rev-parse '@{u}')" ]; then
  echo "Bez zmian ($(git rev-parse --short HEAD))"
  exit 0
fi

git pull --ff-only
echo "Zaktualizowano do $(git rev-parse --short HEAD) — przebudowuję API..."
sudo /usr/local/bin/auto-poster-rebuild.sh
