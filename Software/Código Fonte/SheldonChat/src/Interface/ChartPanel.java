/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Andre
 */
public class ChartPanel extends JPanel{
    private double[] values;

  private String[] names;
  
  private String title2;    //linha acrescentada para exibir a média das notas
  
  private String title;

  public ChartPanel(double[] v, String[] n, String t, String t2) {
    names = n;
    values = v;
    title = t;
    title2 = t2;
    
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (values == null || values.length == 0)
      return;
    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < values.length; i++) {
      if (minValue > values[i])
        minValue = values[i];
      if (maxValue < values[i])
        maxValue = values[i];
    }

    Dimension d = getSize();
    int clientWidth = d.width;
    int clientHeight = d.height;
    int barWidth = 85; // valor modificado

    Font titleFont = new Font("Trebuchet MS", Font.BOLD, 20);
    FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
    Font labelFont = new Font("Trebuchet MS", Font.PLAIN, 12);
    FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);
    
    Font titleFont2 = new Font("Trebuchet MS", Font.PLAIN, 16); //linha acrescentada para mudar a fonte da média das notas

    int titleWidth = titleFontMetrics.stringWidth(title);
    int titleWidth2 = titleFontMetrics.stringWidth(title2); //linha acrescentada para obter a largura da String da média(title2)
    int y = titleFontMetrics.getAscent();
    int x = (clientWidth - titleWidth) / 2;
    g.setFont(titleFont);
    g.drawString(title, x, y);
    g.setFont(titleFont2); //linha acrescentada para mudar a fonte da média das notas
    g.drawString(title2, (clientWidth - titleWidth2)/2 , y+20); //Aqui a altura((clientWidth - titleWidth)/2) funciona como a variável x, porém ajustado para a média ficar alinhada

    int top = titleFontMetrics.getHeight();
    int bottom = labelFontMetrics.getHeight();
    if (maxValue == minValue)
      return;
    double scale = (clientHeight - top - bottom) / (maxValue - minValue);
    y = clientHeight - labelFontMetrics.getDescent() ;
    g.setFont(labelFont);

    for (int i = 0; i < values.length; i++) {
      int valueX = i * barWidth + 1;
      int valueY = top + 20; //+20 para diminuir a altura da barra e caber a média (sim é "+20" para diminuir)
      int height = (int) ((values[i] * scale)-20); //-20 para diminuir a altura do valor escrito da barra e caber a média
      int y2 = clientHeight - labelFontMetrics.getDescent() - height ; //y2 acrescentado para exibir uma label numerica nas barras
      if (values[i] >= 0)
        valueY += (int) ((maxValue - values[i]) * scale);
      else {
        valueY += (int) (maxValue * scale);
        height = -height;
      }

      g.setColor(Color.orange);
      g.fillRect(valueX, valueY, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueX, valueY, barWidth - 2, height);
      int labelWidth = labelFontMetrics.stringWidth(names[i]);
      x = i * barWidth + (barWidth - labelWidth) / 2;
      
      g.drawString(names[i], x, y);
      if (values[i] > 0) {
          g.drawString(String.valueOf(values[i]),x+25, y2); // linha acrescentada para exibir uma label numerica nas barras
      }
      
    }
  }
}