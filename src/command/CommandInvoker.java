/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mrcar
 */
public class CommandInvoker {
    private List<Command> imediatos = new ArrayList<>();
	
	private List<Command> todos = new ArrayList<>();
	
	private List<Command> undo = new ArrayList<>();
	
	public void add(Command comm) {
		imediatos.add(comm);
	}
	
	public void execute() {
		for (Command comm:imediatos) {
			comm.execute();
			todos.add(comm);
		}		
		imediatos.clear();
		
	}
	
	public void undo() {
		Command comm = todos.remove(todos.size()-1);
		comm.undo();
		undo.add(comm);		
	}
	
	
	public void redo() {
		
		Command comm = undo.remove(undo.size()-1);
		comm.redo();
		todos.add(comm);		
	}

	public void imprimir() {		
		System.out.println("Log :");
		for (Command comm:todos) {
			System.out.println(" " + comm);
		}		
	}
	
	public void execute(Command comm) throws Exception{
		comm.execute();
		todos.add(comm);
	}
}
