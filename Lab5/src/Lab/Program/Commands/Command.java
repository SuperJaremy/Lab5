package Lab.Program.Commands;

import Lab.Program.Work;
/**
 * Абстрактный класс для всех команд
 */
public abstract class Command {
    /**
     * Выводит описание команды в стандартный поток вывода
     */
    protected abstract void describe();
    public abstract String getName();

    /**
     * Выполняет действие команды
     * @param work
     * @throws NullPointerException
     */
    public abstract void act(Work work) throws NullPointerException, ExitException;
}
