package Lab.Program.Commands;

import Lab.Program.Work;


/**
 * Заменяет элемент с заданным id элементом из файла
 */
public class FileUpdateID extends UpdateID implements SearchID{
    @Override
    public void act(Work work){
        if(work.getElement()!=null) {
            try {
                int i = Integer.parseInt(work.getElement());
                if (SearchID(work.vector, i) != null){
                    work.setCurrentLine(SearchID(work.vector, i).fromFile(work.getContents(), work.getCurrentLine()+1));
                }
                else{
                    System.out.println("Элемент с id "+i+" не был найден");
                    throw new NullPointerException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Аргумент команды "+name+" должен быть целым числом");
                throw new NullPointerException();
            }
        }else{
            System.out.println("У команды "+name+" должен быть аргумент");
            describe();
            throw new NullPointerException();
        }
    }
}
