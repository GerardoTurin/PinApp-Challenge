


ALTER TABLE devices
  ALTER COLUMN unique_id TYPE VARCHAR(128)
    USING unique_id::VARCHAR(128);