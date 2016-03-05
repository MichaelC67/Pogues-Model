package fr.insee.pogues.mock;

import org.apache.log4j.Logger;

import fr.insee.pogues.model.CodeList;
import fr.insee.pogues.model.CodeListSpecification;
import fr.insee.pogues.model.CodeLists;

public class CodeListFactory {

	public static int MAX_CODE_NUMBER = 7;

	private static final Logger logger = Logger.getLogger(CodeListFactory.class);

	public CodeListFactory() {}

	public CodeList createCodeList() {

		CodeList codeList = new CodeList();

		String listId = "CL_" + (int) Math.floor(Math.random() * 10000);

		logger.debug("Creating code list " + listId);

		codeList.setId(listId);
		codeList.setName("Code list " + listId);
		codeList.setLabel("Label for code list number " + listId);

		int numberOfCodes = (int) Math.floor(Math.random() * (MAX_CODE_NUMBER + 1));
		for (int codeIndex = 0; codeIndex <= numberOfCodes; codeIndex++) {
			CodeList.Code code = new CodeList.Code();
			code.setLabel("Label for code number " + listId + "_" + codeIndex);
			code.setValue(listId + "_" + codeIndex);

			codeList.getCode().add(code);
		}
		logger.debug("Number of codes in list: " + numberOfCodes);

		return codeList;
	}

	public CodeListSpecification createCodeListSpecification() {

		CodeListSpecification codeListSpecification = new CodeListSpecification();

		String specId = "CLS_" + (int) Math.floor(Math.random() * 10000);

		logger.debug("Creating code list specification " + specId);

		codeListSpecification.setId(specId);
		codeListSpecification.setRetrievalQuery("http://id.insee.fr/codes/" + specId.toLowerCase());

		return codeListSpecification;

	}

	public CodeLists createCodeLists(int codeListNumber, int codeListSpecificationNumber) {

		logger.debug("Creating (" + codeListNumber + "," + codeListSpecificationNumber + ") CodeLists object");

		CodeLists codeLists = new CodeLists();

		if (codeListNumber >= 1) {
			for (int codeListIndex = 0; codeListIndex < codeListNumber; codeListIndex++) {
				codeLists.getCodeList().add(this.createCodeList());
			}
		}
		if (codeListSpecificationNumber >= 1) {
			for (int codeListSpecificationIndex = 0; codeListSpecificationIndex < codeListSpecificationNumber; codeListSpecificationIndex++) {
				codeLists.getCodeListSpecification().add(this.createCodeListSpecification());
			}
		}

		return codeLists;
	}

	public CodeLists createCodeLists() {

		return createCodeLists(1, 2);
	}
}
