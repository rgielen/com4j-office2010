package net.nrgie.com4j.office2010.sample.word;

import com4j.EventCookie;
import com4j.Variant;
import net.rgielen.com4j.office2010.word.ClassFactory;
import net.rgielen.com4j.office2010.word.Window;
import net.rgielen.com4j.office2010.word._Application;
import net.rgielen.com4j.office2010.word._Document;
import net.rgielen.com4j.office2010.word.events.ApplicationEvents2;

/**
 * Simple Word demo, adapted from com4j distribution
 */
public class WordDemo {
    public static void main(String[] args) {
        _Application app = ClassFactory.createApplication();

        // subscribe to the event from Word
        EventCookie cookie = app.advise(ApplicationEvents2.class, new ApplicationEvents2() {
            @Override
            public void documentChange() {
                System.out.println("document changed");
            }

            @Override
            public void documentOpen(_Document doc) {
                System.out.println("document opened: "+doc.name());
            }

            @Override
            public void newDocument(_Document doc) {
                System.out.println("new document : "+doc.name());
            }

            @Override
            public void windowActivate(_Document doc, Window w) {
                System.out.println("window activated : "+doc.name()+" : "+w.caption());
            }
        });

        app.visible(true);

        // to open a file
        // Variant _ = Variant.MISSING;
        // app.documents().open("c:\\foo.doc", _, _, _, _, _, _, _, _, _, _, _);

        app.documents().add( Variant.getMissing(), false, false, true);
        app.selection().typeText("Welcome to com4j");
        app.selection().typeParagraph();
        app.selection().typeText("Your Java/COM bridging solution");

        cookie.close();
    }
}
