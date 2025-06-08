/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside fr.eriniumgroup.skyzeradventure as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package fr.eriniumgroup.skyzeradventure;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class PourMesCodesDeTest {
	public PourMesCodesDeTest() {
new Thread(() -> {
	// Traitement secondaire ²

}).start();

		java.util.List<Object> obj = new java.util.ArrayList<>();

		obj = new Object(){
			private List<Object> parsePattern(String patternStr, String input) {
				// Définition des sous-motifs regex pour chaque placeholder
				String numericPattern = "(-?\\d+(?:\\.\\d+)?)";                     // nombre entier ou décimal (Double)
				String stringPattern  = "(?:'([^']*)'|\\\"([^\\\"]*)\\\")";        // texte entre quotes simples ou doubles
				String booleanPattern = "(true|false)";                           // booléen "true" ou "false"

				// Construire le motif regex complet en insérant les motifs correspondants aux placeholders
				StringBuilder regexBuilder = new StringBuilder();
				Pattern placeholderRegex = Pattern.compile("%(NUMERIC|STRING|BOOLEAN)%");
				Matcher phMatcher = placeholderRegex.matcher(patternStr);
				int lastEnd = 0;
				List<String> placeholderTypes = new ArrayList<>();  // garde la liste des types de placeholders dans l'ordre
				while (phMatcher.find()) {
					// Ajouter la partie littérale (fixe) précédant le placeholder actuel, en l'échappant pour le regex
					String literalText = patternStr.substring(lastEnd, phMatcher.start());
					if (!literalText.isEmpty()) {
						regexBuilder.append(Pattern.quote(literalText));
					}
					// Insérer le motif du placeholder selon son type
					String type = phMatcher.group(1);  // "NUMERIC", "STRING" ou "BOOLEAN"
					switch (type) {
						case "NUMERIC":
							regexBuilder.append(numericPattern);
							placeholderTypes.add("NUMERIC");
							break;
						case "STRING":
							regexBuilder.append(stringPattern);
							placeholderTypes.add("STRING");
							break;
						case "BOOLEAN":
							regexBuilder.append(booleanPattern);
							placeholderTypes.add("BOOLEAN");
							break;
					}
					lastEnd = phMatcher.end();
				}
				// Ajouter le reste de texte littéral après le dernier placeholder, le cas échéant
				if (lastEnd < patternStr.length()) {
					String literalText = patternStr.substring(lastEnd);
					regexBuilder.append(Pattern.quote(literalText));
				}
				// Compiler le motif regex final
				Pattern finalPattern = Pattern.compile(regexBuilder.toString());
				Matcher matcher = finalPattern.matcher(input);

				// Vérifier que l'intégralité de la chaîne d'entrée correspond au motif
				if (!matcher.matches()) {
					return new ArrayList<>(); // aucune correspondance complète trouvée, retourner liste vide
				}

				// Extraire les groupes capturés et les convertir dans les bons types
				List<Object> result = new ArrayList<>();
				int groupIndex = 1;
				for (String type : placeholderTypes) {
					switch (type) {
						case "NUMERIC":
							String numStr = matcher.group(groupIndex);
							// Convertir en Double
							result.add(Double.valueOf(numStr));
							groupIndex += 1;
							break;
						case "BOOLEAN":
							String boolStr = matcher.group(groupIndex);
							// Convertir en Boolean (true/false)
							result.add(Boolean.valueOf(boolStr));
							groupIndex += 1;
							break;
						case "STRING":
							// Deux groupes de capture possibles pour le contenu du string (selon quote simple ou double)
							String content = matcher.group(groupIndex);
							if (content == null) {
								content = matcher.group(groupIndex + 1);
							}
							result.add(content);
							groupIndex += 2;  // sauter les deux groupes utilisés par %STRING%
							break;
					}
				}
				return result;
			}
		}.parsePattern("${input$pattern}", "${input$String}");

// Retour au thread principal (client)
Minecraft.getInstance().tell(() -> {

});
	}
}
