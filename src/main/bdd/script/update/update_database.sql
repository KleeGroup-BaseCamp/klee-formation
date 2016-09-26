-- ============================================================
--   Table : INSCRIPTION
-- ============================================================

ALTER TABLE INSCRIPTION ADD CONSTRAINT INS_UNIQUE_UTI_SES UNIQUE(UTI_ID, SES_ID);
