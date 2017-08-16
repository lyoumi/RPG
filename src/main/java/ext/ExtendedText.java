package ext;

import javafx.scene.text.Text;

public class ExtendedText extends Text{

    public ExtendedText(String text) {
        super(text);
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
    }
}
