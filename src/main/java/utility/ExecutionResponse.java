package utility;

/**
 * Класс для ответа выполненной команды
 * @param message сообщение выполнения
 * @param shouldExit флаг для выхода
 */
public record ExecutionResponse(String message, boolean shouldExit) {
}
