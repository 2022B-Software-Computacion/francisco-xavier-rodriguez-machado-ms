package com.example.deber

class KfcUsuarioProveedor {
    companion object{
        val kfcUsuariosLista = listOf<KfcUsuario>(
            KfcUsuario(
                "Pedidos",
                "app/res/drawable/ic_tenedores.xml"
            ),
            KfcUsuario(
                "Datos de Facturación",
                "app/res/drawable/ic_perfil.xml"
            ),
            KfcUsuario(
                "Dirección de entrega",
                "app/res/drawable/ic_mapa.xml"
            ),
            KfcUsuario(
                "Notificaciones",
                "app/res/drawable/ic_notificaciones.xml"
            ),
            KfcUsuario(
                "Editar Perfil",
                "app/res/drawable/ic_lapiz.xml"
            ),
            KfcUsuario(
                "Permisos de notificación",
                "app/res/drawable/ic_notificaciones_tachada.xml"
            ),
            KfcUsuario(
                "Ayuda",
                "app/res/drawable/ic_ayuda.xml"
            ),
            KfcUsuario(
                "Términos legales",
                "app/res/drawable/ic_terminos_legales.xml"
            ),
            KfcUsuario(
                "Cerrar sesión",
                "app/res/drawable/ic_cerrar_sesion.xml"
            )
        )
    }

}