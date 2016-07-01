package converter.abyte.mops1k.biteconverter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);
    }

    protected void ClickConvertButton(View view) {
        Convert converter = new Convert();
        RadioGroup fromGroup = (RadioGroup) findViewById(R.id.fromGroup);
        RadioGroup toGroup = (RadioGroup) findViewById(R.id.toGroup);
        RadioButton fromButton = (RadioButton) findViewById(fromGroup.getCheckedRadioButtonId());
        RadioButton toButton = (RadioButton) findViewById(toGroup.getCheckedRadioButtonId());
        TextView resultView = (TextView) findViewById(R.id.resultView);
        EditText numberText = (EditText) findViewById(R.id.convertNumber);

        converter
            .setFromLevel(this.textToLevel(fromButton.getText().toString()))
            .setToLevel(this.textToLevel(toButton.getText().toString()))
        ;
        if (!numberText.getText().toString().isEmpty()) {
            long result = converter.calculate(Integer.parseInt(numberText.getText().toString()));
            String printable = String.format(
                    Locale.forLanguageTag("ru"),
                    "Результат: %1$d %2$s",
                    result,
                    toButton.getText().toString().toLowerCase()
            );
            resultView.setText(printable);
        }
    }

    protected void CopyResultValue(View view) {
        TextView resultView = (TextView) findViewById(R.id.resultView);
        Pattern pattern = Pattern.compile("^.*: (\\d+) .*");
        Matcher matcher = pattern.matcher(resultView.getText().toString());

        if (matcher.matches()) {
            String result = matcher.group(1);

            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("value", result);
            clipboard.setPrimaryClip(clipData);

            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "Результат скопирован в буфер обмена!",
                    Toast.LENGTH_SHORT
            );
            toast.show();
        }
    }

    private int textToLevel(String text) {
        int level = 1;
        switch (text) {
            case "Бит":
                level = 1;
                break;
            case "Байт":
                level = 2;
                break;
            case "Килобайт":
                level = 3;
                break;
            case "Мегабайт":
                level = 4;
                break;
            case "Гигабайт":
                level = 5;
                break;
            case "Терабайт":
                level = 6;
                break;
            default:
                level = 1;
                break;
        }

        return level;
    }
}
