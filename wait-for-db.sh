#!/bin/sh

echo "Wainting to database..."

until pg_isready -h db -p 5432 -U postgres; do
  echo "Database is still unavailable..."
  sleep 1
done

echo "Database ready. Setup backend..."

exec "$@"