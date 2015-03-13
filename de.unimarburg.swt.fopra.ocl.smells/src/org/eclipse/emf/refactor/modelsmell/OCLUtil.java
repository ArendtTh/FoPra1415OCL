package org.eclipse.emf.refactor.modelsmell;

import java.util.LinkedList;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.Root;
import org.eclipse.ocl.examples.xtext.base.basecs.PivotableElementCS;
import org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.CompleteOCLDocumentCS;

public class OCLUtil {

	public static Root getOCLPivotRoot(EObject root) {
		if (root instanceof CompleteOCLDocumentCS) {
			CompleteOCLDocumentCS documentCS = (CompleteOCLDocumentCS) root;
			return (Root) documentCS.getPivot();
		}
		return null;
	}

	public static EList<EObject> getAllOCLElementsFromRoot(Root oclRoot) {
		EList<EObject> list = new BasicEList<EObject>();
		TreeIterator<EObject> iter = oclRoot.eAllContents();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

	public static void addModelSmell(LinkedList<LinkedList<EObject>> results,
			EObject elem, EObject root) {
		LinkedList<EObject> modelSmell = new LinkedList<EObject>();
		modelSmell.add(elem);
		if (root instanceof CompleteOCLDocumentCS) {
			EList<PivotableElementCS> pivots = getAllPivotables((CompleteOCLDocumentCS) root);
			for (PivotableElementCS pivot : pivots) {
				if (pivot.getPivot() == elem) {
					modelSmell.add(pivot);
				}
			}
		}
		results.add(modelSmell);
	}

	public static void addModelSmell(LinkedList<LinkedList<EObject>> results,
			EList<EObject> elems, EObject root) {
		LinkedList<EObject> modelSmell = new LinkedList<EObject>();
		for (EObject elem : elems) {
			modelSmell.add(elem);
			if (root instanceof CompleteOCLDocumentCS) {
				EList<PivotableElementCS> pivots = getAllPivotables((CompleteOCLDocumentCS) root);
				for (PivotableElementCS pivot : pivots) {
					if (pivot.getPivot() == elem) {
						modelSmell.add(pivot);
					}
				}
			}
		}
		results.add(modelSmell);
	}

	private static EList<PivotableElementCS> getAllPivotables(
			CompleteOCLDocumentCS root) {
		EList<PivotableElementCS> list = new BasicEList<PivotableElementCS>();
		TreeIterator<EObject> iter = root.eAllContents();
		while (iter.hasNext()) {
			EObject elem = iter.next();
			if (elem instanceof PivotableElementCS) {
				list.add((PivotableElementCS) elem);
			}
		}		
		return list;
	}

	public static EList<EObject> getAllOCLElementsFromInvariant(
			ExpressionInOCL inv) {
		EList<EObject> list = new BasicEList<EObject>();
		TreeIterator<EObject> iter = inv.eAllContents();
		while (iter.hasNext()) {
			list.add(iter.next());
		}
		return list;
	}

}
