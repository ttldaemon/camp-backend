CREATE TABLE camps (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       name VARCHAR(100) NOT NULL,
                       slug VARCHAR(100) NOT NULL UNIQUE,
                       description TEXT,
                       avatar_url TEXT,
                       visibility VARCHAR(20) NOT NULL DEFAULT 'PUBLIC',
                       owner_id UUID NOT NULL REFERENCES users(id),
                       tags TEXT[],
                       member_count INTEGER NOT NULL DEFAULT 1,
                       created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                       updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                       deleted_at TIMESTAMPTZ
);

CREATE INDEX idx_camps_slug ON camps(slug);
CREATE INDEX idx_camps_owner ON camps(owner_id);