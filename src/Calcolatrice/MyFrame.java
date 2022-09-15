package Calcolatrice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*  
     Non funziona l'inserimento di numeri negativi tipo -5 -5.
     Non rispetta l'ordine delle operazioni, vengono fatte tutte sequenzialmente.
*/
/**
 *
 * @author Riccardo Pacini e Giacomo Orsucci 4IC
 */
public class MyFrame extends JFrame implements ActionListener
{
    private JPanel panel1;
    private JTextField testo;
    private JPanel textPanelN; // text panel north
    private JPanel buttonPanelC; // button panel center
    private JPanel buttonPanelE; // button panel east
    private JButton but1, but2, but3, but4, but5, but6, but7, but8, but9, but0, butC, butPunto; //bottoni del panel center
    private JButton butPiu, butMeno, butPer, butDiv, butUguale; //bottoni del panel east
    private String schermo = "", ultimaOperazione = "";
    private float risultato = 0, operando = 0;
    private boolean controlloZeroIniziale = false; //varibiale per il controllo degli 0 davanti al numero
    private boolean eccezione = false;
    
    public MyFrame(String titolo)
    {
        super(titolo);
        setBounds(200, 100, 400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //instanzio il pannello
        panel1 = (JPanel)getContentPane();
        
        //instanzio i JTextField
        testo = new JTextField();
        //faccio partire da destra
        testo.setHorizontalAlignment(JTextField.RIGHT);
        
        //instanzio i Panel
        textPanelN = new JPanel();
        buttonPanelC = new JPanel();
        buttonPanelE = new JPanel();
        
        //instanzio i bottoni del butttonPanel center
        but7 = new JButton("7");
        but8 = new JButton("8");
        but9 = new JButton("9");
        but4 = new JButton("4");
        but5 = new JButton("5");
        but6 = new JButton("6");
        but1 = new JButton("1");
        but2 = new JButton("2");
        but3 = new JButton("3");
        butC = new JButton("C");
        but0 = new JButton("0");
        butPunto = new JButton(".");
        
        //instanzio i bottoni del buttonPanel east
        butPiu = new JButton("+");
        butMeno = new JButton("-");
        butPer = new JButton("x");
        butDiv = new JButton("/");
        butUguale = new JButton("=");
        
        
        //setto il pannello generale della finestra
        panel1.setLayout(new BorderLayout()); // inutile perchè è gia di default
        
        //setto il buttoPanel center
        buttonPanelC.setLayout(new GridLayout(4,3));
        buttonPanelC.add(but7);
        buttonPanelC.add(but8);
        buttonPanelC.add(but9);
        buttonPanelC.add(but4);
        buttonPanelC.add(but5);
        buttonPanelC.add(but6);
        buttonPanelC.add(but1);
        buttonPanelC.add(but2);
        buttonPanelC.add(but3);
        buttonPanelC.add(butC);
        buttonPanelC.add(but0);
        buttonPanelC.add(butPunto);
        
        //setto il font dei bottoni
        Font f = new Font("Arial", Font.PLAIN, 40);
        but1.setFont(f);
        but2.setFont(f);
        but3.setFont(f);
        but4.setFont(f);
        but5.setFont(f);
        but6.setFont(f);
        but7.setFont(f);
        but8.setFont(f);
        but9.setFont(f);
        but0.setFont(f);
        butC.setFont(f);
        butPunto.setFont(f);
        butPiu.setFont(f);
        butMeno.setFont(f);
        butPer.setFont(f);
        butDiv.setFont(f);
        butUguale.setFont(f);
         
        //setto il buttoPanel east
        buttonPanelE.setLayout(new GridLayout(5,1));
        buttonPanelE.add(butPiu);
        buttonPanelE.add(butMeno);
        buttonPanelE.add(butPer);
        buttonPanelE.add(butDiv);
        buttonPanelE.add(butUguale);
        buttonPanelE.setPreferredSize(new Dimension (100, 600));
         
        //setto il textPanel north
        textPanelN.add(testo);
        
        //setto il testo
        testo.setPreferredSize(new Dimension (380, 150));
        testo.setFont(new Font("Arial", Font.PLAIN, 50));
        testo.setEditable(false);
        
        //aggiungo i listener
        but1.addActionListener(this);
        but2.addActionListener(this);
        but3.addActionListener(this);
        but4.addActionListener(this);
        but5.addActionListener(this);
        but6.addActionListener(this);
        but7.addActionListener(this);
        but8.addActionListener(this);
        but9.addActionListener(this);
        but0.addActionListener(this);
        butC.addActionListener(this);
        butPunto.addActionListener(this);
        butPiu.addActionListener(this);
        butMeno.addActionListener(this);
        butPer.addActionListener(this);
        butDiv.addActionListener(this);
        butUguale.addActionListener(this);
        
        //aggiungo i vari componenti al panel1
        panel1.add(textPanelN, BorderLayout.NORTH);
        panel1.add(buttonPanelC, BorderLayout.CENTER);
        panel1.add(buttonPanelE, BorderLayout.EAST);
    }
    /*
        LOGICA DEL PROGRAMMA E FUNZIONAMENTO
        Guardo sempre l'operazione precedente, la quale verrà eseguita dall'operatore successivo,
        quindi l'= effettuerà l'ultima operazione e stamperà il risultato. Perciò nei vari if controllo
        qual'è stata l'ultima operazione selezionata, la eseguo e imposto come ultima operazione quella seguente.
        Se l'operazione precedente era niente allora mi limito ad impostare come risultato l'operando inserito
        e ad impostare l'ultima operazione selezionata come la seguente.
        L'operando viene preso tutte le volte dallo schermo, così si possono fare operazioni anche con il risultato
        dell'operazione precedente (uscito premendo =).
    */
    @Override
    public void actionPerformed(ActionEvent ae) 
    {   
        //guardo l'origine dell'evento
        JButton origine = (JButton) ae.getSource();
        
        switch(origine.getText())
        {   
            case "0":
                if(controlloZeroIniziale == true);
                {   
                    schermo = schermo + origine.getText();
                }
                if((controlloZeroIniziale == false) || (ultimaOperazione.equalsIgnoreCase("=") == true))
                {
                    schermo = "0";
                    ultimaOperazione = "";
                }
                break;
            case "C":  //azzero tutto 
                schermo = "";
                risultato = 0;
                operando = 0;
                controlloZeroIniziale = false;
                eccezione = false;
                ultimaOperazione = "";
                break;
            case "+":
                if(ultimaOperazione.equalsIgnoreCase("")) 
                {   
                    operando = converti();
                    ultimaOperazione = "+";
                    risultato = operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("+"))
                {   
                    operando = converti();
                    ultimaOperazione = "+";
                    risultato = risultato + operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("-"))
                {   
                    risultato = risultato - operando;
                    ultimaOperazione = "+";
                }
                else if(ultimaOperazione.equalsIgnoreCase("x"))
                {   
                    risultato = risultato * operando;
                    ultimaOperazione = "+";
                }
                else if(ultimaOperazione.equalsIgnoreCase("/"))
                {   
                    risultato = risultato / operando;
                    ultimaOperazione = "+";
                }
                schermo = "";
                break;
            case "-":
                if(ultimaOperazione.equalsIgnoreCase(""))
                {   
                    operando = converti();
                    ultimaOperazione = "-";
                    risultato = operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("-"))
                {   
                    operando = converti();
                    ultimaOperazione = "-";
                    risultato = risultato - operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("+"))
                {   
                    risultato = risultato + operando;
                    ultimaOperazione = "-";
                }
                else if(ultimaOperazione.equalsIgnoreCase("x"))
                {   
                    risultato = risultato * operando;
                    ultimaOperazione = "-";
                }
                else if(ultimaOperazione.equalsIgnoreCase("/"))
                {   
                    risultato = risultato / operando;
                    ultimaOperazione = "-";
                } 
                schermo = "";
                break;
            case "x":
                if(ultimaOperazione.equalsIgnoreCase(""))
                {   
                    operando = converti();
                    ultimaOperazione = "x";
                    risultato = operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("x"))
                {   
                    operando = converti();
                    ultimaOperazione = "x";
                    risultato = risultato * operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("+"))
                {   
                    risultato = risultato + operando;
                    ultimaOperazione = "x";
                }
                else if(ultimaOperazione.equalsIgnoreCase("-"))
                {   
                    risultato = risultato - operando;
                    ultimaOperazione = "x";
                }
                else if(ultimaOperazione.equalsIgnoreCase("/"))
                {   
                    risultato = risultato / operando;
                    ultimaOperazione = "x";
                }
                schermo = "";
                break;
            case "/":
                if(ultimaOperazione.equalsIgnoreCase(""))
                {   
                    operando = converti();
                    ultimaOperazione = "/";
                    risultato = operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("/"))
                {   
                    operando = converti();
                    ultimaOperazione = "/";
                    risultato = risultato / operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("+"))
                {   
                    risultato = risultato + operando;
                    ultimaOperazione = "/";
                }
                else if(ultimaOperazione.equalsIgnoreCase("-"))
                {   
                    risultato = risultato - operando;
                    ultimaOperazione = "/";
                }
                else if(ultimaOperazione.equalsIgnoreCase("x"))
                {   
                    risultato = risultato * operando;
                    ultimaOperazione = "/";
                }
                schermo = "";
                break;
            case "=":
                if(ultimaOperazione.equalsIgnoreCase("+"))
                {   
                    operando = converti();
                    risultato = risultato + operando;
                }
                if(ultimaOperazione.equalsIgnoreCase("-"))
                {   
                    operando = converti();
                    risultato = risultato - operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("x"))
                {   
                    operando = converti();
                    risultato = risultato * operando;
                }
                else if(ultimaOperazione.equalsIgnoreCase("/"))
                {   
                    operando = converti();
                    risultato = risultato / operando;
                }
                //azzero le operazioni eseguite
                ultimaOperazione = "";
                //riconverto in stringa il risultato
                schermo = String.valueOf(risultato);
                risultato = 0;
                break;
            default: //se il bottone premuto è un numero (tranne 0)
                if(ultimaOperazione.equalsIgnoreCase("="))
                {   
                    schermo = origine.getText();
                }
                else
                {   
                    schermo = schermo + origine.getText();
                }
                operando = converti();
                controlloZeroIniziale = true;
                break;
        } 
        testo.setText(schermo);
        if(eccezione == true)
        {
            testo.setText("");
            operando = 0;
            risultato = 0;
            schermo = "";
            eccezione = false;
        }
    }
    private float converti()
    {
        try//provo a convertire
        {
            operando = Float.parseFloat(schermo);
        }catch(NumberFormatException e)
        {   
            testo.setText("");
            JOptionPane.showMessageDialog(null, "Inserire nuovamente il numero", "Errore", JOptionPane.WARNING_MESSAGE);
            eccezione = true;
        }
        return operando;
    }
}
