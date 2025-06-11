INSERT INTO oauth2_registered_client (-- [1] Identificador único interno
    client_id,                     -- [2] Identificador público del cliente (usado por curl/Postman)
    client_id_issued_at,           -- [3] Fecha/hora en que se creó el cliente
    client_secret,                 -- [4] Contraseña secreta para autenticar (puede ir cifrada)
    client_secret_expires_at,      -- [5] (opcional) fecha de expiración del secreto
    client_name,                   -- [6] Nombre descriptivo (visible en UI si hubiera)
    client_authentication_methods, -- [7] Métodos que puede usar para autenticarse
    authorization_grant_types,     -- [8] Tipos de flujo que puede usar (`client_credentials`, `authorization_code`, etc.)
    redirect_uris,                 -- [9] (opcional) redirecciones permitidas (solo aplica a `authorization_code`)
    scopes,                        -- [10] Permisos que puede solicitar (`read`, `write`, etc.)
    client_settings,               -- [11] Configuración avanzada en JSON
    token_settings                 -- [12] Configuración de los tokens generados (expiración, refresh, etc.)
)
VALUES (-- [1] UUID auto generado
   'bitacora-clientes',                                                       -- [2] client_id que usarás en curl/postman
   now(),                                                             -- [3] se emite ahora
   '{noop}secret-clientes',                                                -- [4] sin codificar (solo para pruebas)
   null,                                                          -- [5] no expira el secreto
   'Bitacora Clientes',                                                    -- [6] nombre legible
   'client_secret_basic',                                    -- [7] común (HTTP Basic con id:secret)
   'client_credentials',                                        -- [8] flujo usado por microservicios
   '',                                                                     -- [9] no aplica para client_credentials
   'read write',                                                               -- [10] permisos que tendrá el token
   '{"require_proof_key":false,"require_authorization_consent":false}',  -- [11]
   '{"access_token_ttl":"PT1H","reuse_refresh_tokens":true}'             -- [12]
);