package gradle.plugins.mqsicreatebar

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Task which executes the IBM mqsicreatebar command
 * 
 * @author Jamie Archibald
 *
 */
class CreateApplicationBarTask extends DefaultTask {

	@TaskAction
	def createBar() {
		
		def barFileName = "build/" + project.name.replaceAll(" ", "_") + "-" + project.version + ".bar"
			
		// TODO check the .project file
		// if the project is an Application then
		// if it is a regular integration project then user needs to define what flows should be included
		
		if (new File(barFileName).exists() == false) {
		
			println "Creating $barFileName"
		
			def cmd = "mqsicreatebar -data ../ -b $barFileName -a \"${project.name}\" -version ${project.version} -cleanBuild -deployAsSource"
			println cmd
			
			def process = cmd.execute()
			println process.text
			if (process.exitValue() != 0) {
				throw new Exception("mqsicreatebar failed. Error code " + process.exitValue())
			}
			
		} else {
			// bar file already created, skip.
			// user must perform a clean
		
			// com.ibm.etools.msgbroker.tooling.applicationNature
		}
	}
}