package by.ystar;

import com.google.common.io.Files;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ScreenshotTestRule implements MethodRule {
    public Statement apply(final Statement statement, final FrameworkMethod frameworkMethod, final Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    statement.evaluate();
                    captureScreenshot(frameworkMethod.getName());
                    captureVideo();
                } catch (Throwable t) {
                    captureScreenshot(frameworkMethod.getName());
                    captureVideo();
                    throw t; // rethrow to allow the failure to be reported to JUnit
                }
            }

            @Attachment(value = "{0}", type = "image/png")
            private byte[] captureScreenshot(String methodName) throws IOException {
                File screenshot = makeScreenshot();
                //     FileUtils.copyFile(screenshot, new File("target\\allure-results\\screenshots\\" + screenshot.getFirstName()));
                return Files.toByteArray(screenshot);
            }

            @Attachment(value = "video", type = "video/avi")
            public byte[] captureVideo() throws InterruptedException {
                try {
                    Thread.sleep(15 * 1000);
                    File folder = new File("D:\\automation_tests\\yandex\\video");
                    File[] listOfFiles = folder.listFiles();

                    for (int i = 0; i < listOfFiles.length; i++) {
                        if (listOfFiles[i].isFile()) {
                            return java.nio.file.Files.readAllBytes(Paths.get(listOfFiles[i].getAbsolutePath()));
                        }
                    }
                    Thread.sleep(15 * 1000);
                } catch (IOException e) {
                    return new byte[0];
                }
                return new byte[0];
            }

            private File makeScreenshot() {
                return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            }
        };
    }
}