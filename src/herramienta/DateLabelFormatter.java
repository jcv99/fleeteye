package herramienta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

import constante.Messages;
public class DateLabelFormatter extends AbstractFormatter {

    private String datePattern = Messages.getString("DateLabelFormatter.0"); //$NON-NLS-1$
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return Messages.getString("VACIO"); //$NON-NLS-1$
    }
}
