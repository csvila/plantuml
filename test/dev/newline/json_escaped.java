package dev.newline;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.GeneratedImage;
import net.sourceforge.plantuml.SourceFileReader;
import net.sourceforge.plantuml.preproc.Defines;

/*
 * 

https://github.com/plantuml/plantuml/issues/1334
@startjson
{
    "a": true,
    "desc": "a\\nb\\nc\\nd\\ne\\nf",
    "required": [
        "r1",
        "r2",
        "r3"
    ],
    "addP": false,
    "properties": {
        "P": "{ ... }"
    },
    "allOf": [
        "{ ... }",
        "{ ... }",
        "{ ... }"
    ]
}
@endjson



 */
public class json_escaped {

	protected File getJavaFile() {
		final String name = getClass().getName();
		final File f = new File("test/" + name.replace('.', '/') + ".java");
		return f;
	}

	@Test
	public void testExecute() throws IOException, InterruptedException {
		final File file = getJavaFile();
		final FileFormatOption options = new FileFormatOption(FileFormat.PNG);

		final File outputDirectory = new File("outputdev").getAbsoluteFile();
		outputDirectory.mkdirs();

		final SourceFileReader reader = new SourceFileReader(Defines.createWithFileName(file), file, outputDirectory,
				Arrays.asList("!pragma layout smetana"), "UTF-8", options);
		final List<GeneratedImage> list = reader.getGeneratedImages();

		assertEquals(1, list.size());

	}


}