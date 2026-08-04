CREATE TABLE camp_members (
          id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
          camp_id UUID NOT NULL REFERENCES camps(id) ON DELETE CASCADE,
          user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
          role VARCHAR(20) NOT NULL DEFAULT 'MEMBER',
          joined_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
          UNIQUE(camp_id, user_id)
);

CREATE INDEX idx_camp_members_user ON camp_members(user_id);
CREATE INDEX idx_camp_members_camp ON camp_members(camp_id);