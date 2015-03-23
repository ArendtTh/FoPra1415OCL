package org.eclipse.emf.refactor.modelsmell;

import java.util.LinkedList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.ocl.examples.pivot.Root;
import org.eclipse.ocl.examples.pivot.*;

public class MagicLiteral implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// start custom code
		Root oclRoot = OCLUtil.getOCLPivotRoot(root);
		if (oclRoot != null) {
			EList<EObject> allOCLElements = OCLUtil.getAllOCLElementsFromRoot(oclRoot);
			for (EObject elem : allOCLElements) {
				if (elem instanceof PrimitiveLiteralExp) {
					OCLUtil.addModelSmell(results, elem, root);
				}
			}
		}
		// end custom code
		return results;
	}

}
