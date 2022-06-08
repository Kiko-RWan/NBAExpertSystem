import net.sourceforge.jFuzzyLogic.FIS;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.nio.file.Paths;

class PlayerAssessment {
	public double defensive;
	public double shooting;
	public double rebounding;
	public double leading;
	public double breaking;
}

class Player {
	public String Player;
	public String pos;
	public double GS;
	public double MP;
	public double FG;
	public double FGA;
	public double FGp;
	public double threeP;
	public double threePA;
	public double threePp;
	public double twoP;
	public double twoPA;
	public double twoPp;
	public double eFGp;
	public double FT;
	public double FTA;
	public double FTp;
	public double ORB;
	public double DRB;
	public double TRB;
	public double AST;
	public double STL;
	public double BLK;
	public double TOV;
	public double PF;
	public double PTS;

	public PlayerAssessment assessment;
}

public class NBA {
	public static void main(String[] args) throws Exception {
		String inputDir = "./input/";
		
		File inputFileRoot = new File(inputDir);
		for (File file : inputFileRoot.listFiles()) {
			if (file.isFile()) {
				Player player = JSON.parseObject(readFile(file), Player.class);
				player.assessment = assess(player);
				writeFile(file, JSON.toJSONString(player, true));
			}
		}
	}

	private static String readFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return new String(buffer);
	}

	private static void writeFile(File file, String content) throws IOException {
		PrintWriter out = new PrintWriter(Paths.get("result", file.getName()).toString());
        out.print(content);
        out.close();
	}

	private static PlayerAssessment assess(Player player) {
		PlayerAssessment assessment = new PlayerAssessment();
		String fclFile = "./NBA.fcl";

		FIS fis = FIS.load(fclFile, true);

		fis.setVariable("GS", player.GS);
		fis.setVariable("MP", player.MP);
		fis.setVariable("FG", player.FG);
		fis.setVariable("FGA", player.FGA);
		fis.setVariable("FGp", player.FGp);
		fis.setVariable("threeP", player.threeP);
		fis.setVariable("threePA", player.threePA);
		fis.setVariable("threePp", player.threePp);
		fis.setVariable("twoP", player.twoP);
		fis.setVariable("twoPA", player.twoPA);
		fis.setVariable("twoPp", player.twoPp);
		fis.setVariable("eFGp", player.eFGp);
		fis.setVariable("FT", player.FT);
		fis.setVariable("FTA", player.FTA);
		fis.setVariable("FTp", player.FTp);
		fis.setVariable("ORB", player.ORB);
		fis.setVariable("DRB", player.DRB);
		fis.setVariable("TRB", player.TRB);
		fis.setVariable("AST", player.AST);
		fis.setVariable("STL", player.STL);
		fis.setVariable("BLK", player.BLK);
		fis.setVariable("TOV", player.TOV);
		fis.setVariable("PF", player.PF);
		fis.setVariable("PTS", player.PTS);

		fis.evaluate();

		assessment.defensive = fis.getVariable("defensive").defuzzify();
		assessment.shooting = fis.getVariable("shooting").defuzzify();
		assessment.rebounding = fis.getVariable("rebounding").defuzzify();
		assessment.leading = fis.getVariable("leading").defuzzify();
		assessment.breaking = fis.getVariable("breaking").defuzzify();

		return assessment;
	}

}