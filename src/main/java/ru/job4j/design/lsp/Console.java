package ru.job4j.design.lsp;
/**
 * Псевдокод.
 * if (thatConsole instanceOf Sony) {
 *     play in Sony Exclusive
 * } else if (thatConsole instanceOf Microsoft) {
 *     play in Strategy and Indie
 * }
 * Такой код нарушает принцип подстановки. Что если подставить консоль от Nintendo, или SteamMachine?
 * В таком случае придётся дописывать код. Что приводит нас к нарушению принципа OCP.
 */
public class Console {
}
