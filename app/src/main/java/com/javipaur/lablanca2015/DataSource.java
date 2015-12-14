package com.javipaur.lablanca2015;

import java.util.ArrayList;
import java.util.List;


public class DataSource {

    static List<ProgramaClass> PROGRAMAS=new ArrayList<ProgramaClass>();

static{

    PROGRAMAS.add(new ProgramaClass(1,"Dia 09","09:00","Colegio Salesiano","1","Imposición de la pañoleta de las Fiestas de San Lorenzo a san Juan Bosco.","1"));
    PROGRAMAS.add(new ProgramaClass(6,"Dia 12","11:20","Plaza Catedral","0","Se izara la bandera de España y Francia","0"));
    PROGRAMAS.add(new ProgramaClass(7,"Dia 13","11:00","Palacio Municipal.","1","Discursos y entrega de la Parrilla de Oro San Lorenzo 2014  a la Agrupación Astronómica de Huesca. ","0"));
    PROGRAMAS.add(new ProgramaClass(8,"Dia 09","12:00","Plaza Navarra","0","Concierto Aurin","1"));

    }

}
