/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import builder.TabuleiroBuilder;

/**
 *
 * @author mrcar
 */
public class Director {
   private TabuleiroBuilder tabuleiroB;
   
    public Director(TabuleiroBuilder tabuleiroB) {
	this.tabuleiroB = tabuleiroB;
    }
	
    public void construir() {         
        tabuleiroB.reset();
        tabuleiroB.build();
    }
}
