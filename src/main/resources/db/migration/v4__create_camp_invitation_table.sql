CREATE TABLE camp_invitations (
              id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
              camp_id UUID NOT NULL REFERENCES camps(id) ON DELETE CASCADE,
              invited_email VARCHAR(255) NOT NULL,
              invited_by UUID NOT NULL REFERENCES users(id),
              role VARCHAR(20) NOT NULL DEFAULT 'MEMBER',
              status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
              created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
              updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_camp_invitations_camp ON camp_invitations(camp_id);
CREATE INDEX idx_camp_invitations_email ON camp_invitations(invited_email);