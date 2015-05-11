package jbehave.statuscalculation;

import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class MailItemStatusCalculatorStories extends JUnitStories{

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(),
				new MailitemStatusCalculationsTest());
	}
	
	@Override
	protected List<String> storyPaths() {
		/*
		 * *.story files are located in the same location as this class. The
		 * maven jbehave embedable plugin should be set-up to move *.story files
		 * across to the target directory. To exclude particular stories add an
		 * excludes filter to the plugin.
		 */
		return new StoryFinder().findPaths(
				CodeLocations.codeLocationFromClass(this.getClass()),
				"**/*StatusCalculation*.story", "");
	}
	
	// Here we specify the configuration, starting from default
	// MostUsefulConfiguration, and changing only what is needed
	@Override
	public Configuration configuration() {
		// Object cl = this.getClass().getClassLoader();
		return new MostUsefulConfiguration()
		// where to find the stories
				.useStoryLoader(new LoadFromClasspath(this.getClass()))
				// CONSOLE and TXT reporting
				.useStoryReporterBuilder(
						new StoryReporterBuilder().withDefaultFormats()
								.withFormats(Format.CONSOLE, Format.TXT,
										Format.HTML, Format.XML))
				;
	}	

}
