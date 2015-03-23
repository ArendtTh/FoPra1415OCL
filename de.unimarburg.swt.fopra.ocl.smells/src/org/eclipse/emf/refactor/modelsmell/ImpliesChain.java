package org.eclipse.emf.refactor.modelsmell;

import java.util.LinkedList;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.smells.interfaces.IModelSmellFinder;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.Root;
import org.eclipse.ocl.examples.pivot.OperationCallExp;

public class ImpliesChain implements IModelSmellFinder {

	@Override
	public LinkedList<LinkedList<EObject>> findSmell(EObject root) {
		LinkedList<LinkedList<EObject>> results = new LinkedList<LinkedList<EObject>>();
		// start custom code
		Root oclRoot = OCLUtil.getOCLPivotRoot(root);
		if (oclRoot != null) {
			EList<EObject> allOCLElements = OCLUtil.getAllOCLElementsFromRoot(oclRoot);
			for (EObject elem : allOCLElements) {
				if (elem instanceof ExpressionInOCL) {
					ExpressionInOCL inv = (ExpressionInOCL) elem;
					EList<EObject> usages = new BasicEList<EObject>();
					EList<EObject> allInvariantElements = OCLUtil.getAllOCLElementsFromInvariant(inv);
					for (EObject invElem : allInvariantElements) {
						if (invElem instanceof OperationCallExp) {
							OperationCallExp OpCExp = (OperationCallExp) invElem;
							if (OpCExp.getReferredOperation().getName().equals("implies" )) {
								usages.add(OpCExp);
							}
						}
					}
					if (usages.size() > 1) {
						OCLUtil.addModelSmell(results, usages, root);
					}
				}
			}
		}
		// end custom code
		return results;
	}

}
