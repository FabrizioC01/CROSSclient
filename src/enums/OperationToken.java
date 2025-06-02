package enums;

/**
 * Lista operazioni che possono essere richieste al server.
 */
public enum OperationToken {
    register,
    login,
    updateCredentials,
    logout,
    insertLimitOrder,
    insertMarketOrder,
    insertStopOrder,
    cancelOrder,
    getPriceHistory
}