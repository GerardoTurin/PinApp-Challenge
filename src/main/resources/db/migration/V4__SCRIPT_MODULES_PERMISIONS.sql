-- Parámetro: asigna el role_id que desees
DO $$
    DECLARE
        role_id_param BIGINT := (SELECT id FROM roles WHERE name = 'SOMAS_SUPER_ADMIN'); -- Puedes cambiar este valor según el rol a asignar
        perm RECORD;
        mod RECORD;
        menu RECORD;
    BEGIN
        -- 1. Insertar permisos para módulos que NO tienen hijos en menu_items
        FOR mod IN (
            SELECT id FROM modules_app
            WHERE id NOT IN (SELECT DISTINCT module_app_id FROM menu_items WHERE module_app_id IS NOT NULL)
        ) LOOP
                -- Insertar un permiso para cada módulo sin hijos
                FOR perm IN (SELECT id FROM permission_types) LOOP
                        INSERT INTO modules_roles_permissions (role_id, permission_id, module_id, menu_item_id)
                        VALUES (role_id_param, perm.id, mod.id, NULL);
                    END LOOP;
            END LOOP;

        -- 2. Insertar permisos para módulos que SÍ tienen hijos en menu_items
        FOR menu IN (
            SELECT mi.id AS menu_item_id, mi.module_app_id AS module_id
            FROM menu_items mi
            WHERE mi.module_app_id IS NOT NULL
        ) LOOP
                -- Insertar un permiso para cada menú hijo
                FOR perm IN (SELECT id FROM permission_types) LOOP
                        INSERT INTO modules_roles_permissions (role_id, permission_id, module_id, menu_item_id)
                        VALUES (role_id_param, perm.id, menu.module_id, menu.menu_item_id);
                    END LOOP;
            END LOOP;
    END $$;