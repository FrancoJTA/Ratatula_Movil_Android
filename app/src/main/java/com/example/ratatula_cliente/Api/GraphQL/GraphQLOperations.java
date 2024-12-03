package com.example.ratatula_cliente.Api.GraphQL;

public class GraphQLOperations {
    // Query: Obtener usuarios
    public static final String GET_LOCALES =
            "query ObtenerLocales { obtenerLocales { id nombre apellido correo rol estado telefono fechaRegistro descripcion categoria horarios { apertura cierre } } }";

    // Query: Obtener Usuario
    public static final String GET_USUARIO =
            "query Query($token: String) { obtenerUsuario(token: $token) { id nombre apellido correo rol telefono idCarrito } }";
    // Mutation: Autenticar Usuario
    public static final String AUTENTICAR_USUARIO =
            "mutation AutenticarUsuario($input: inputAutenticar) { autenticarUsuario(input: $input) { token } }";

    public static final String GET_PRODUCTOS =
            "query ObtenerProductos { obtenerProductos { id nombre descripcion precio stock idLocal } }";

    public static final String GET_PROMOS =
            "query ObtenerPromociones { obtenerPromociones { id nombre descripcion precioReal precioPromo productos { idProducto cantidad } imagen idLocal } }";

    public static final String GET_PRODUCTOS_LOCAL =
            "query ObtenerProductosLocal($idLocal: ID!) { obtenerProductosLocal(idLocal: $idLocal) { id nombre descripcion precio stock idLocal } }";

    public static final String GET_PROMOS_LOCAL =
            "query ObtenerPromocionesLocal($idLocal: ID!) { obtenerPromocionesLocal(idLocal: $idLocal) { id nombre descripcion precioReal precioPromo productos { idProducto cantidad } imagen idLocal } }";

    public static final String ADD_PRODUCT_CARRITO =
            "mutation AgregarProductoACarrito($idUsuario: ID!, $producto: ProductoEnCarritoInput!) { agregarProductoACarrito(idUsuario: $idUsuario, producto: $producto) { id } }";

    public static final String QUITAR_PRODUCT_CARRITO =
                "mutation EliminarProductoDeCarrito($idUsuario: ID!, $idProducto: ID!) { eliminarProductoDeCarrito(idUsuario: $idUsuario, idProducto: $idProducto) { id } }";

    public static final String GET_CARRITO =
            "query Query($idUsuario: ID!) { obtenerCarrito(idUsuario: $idUsuario) { id idUsuario productos { idProducto cantidad } promociones { idPromo cantidad } total fechaActualizacion idLocal } }";

    public static final String CONFIRM_CARRITO =
            "mutation ConfirmarCarrito($idUsuario: ID!, $qr: Boolean!) { confirmarCarrito(idUsuario: $idUsuario, qr: $qr) { id } }";

    public static final String GET_PEDIDO =
            "query ObtenerPedidoPorID($obtenerPedidoPorIdId: ID!) { obtenerPedidoPorID(id: $obtenerPedidoPorIdId) { id idCliente idLocal productos { idProducto cantidad } promociones { idPromo cantidad } tiempoPreparacion estado estadoVenta total fechaCreacion urlqr qr transaccionID } }";

}