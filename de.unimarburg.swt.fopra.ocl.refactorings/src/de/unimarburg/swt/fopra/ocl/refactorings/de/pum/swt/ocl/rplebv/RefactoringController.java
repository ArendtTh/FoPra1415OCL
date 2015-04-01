/**
 * <copyright>
 * </copyright>
 *
 * $Id: RefactoringController.javajet,v 1.2 2012/10/16 21:03:02 tarendt Exp $
 */
package de.unimarburg.swt.fopra.ocl.refactorings.de.pum.swt.ocl.rplebv;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.refactor.refactoring.core.Refactoring;
import org.eclipse.emf.refactor.refactoring.interfaces.IController;
import org.eclipse.emf.refactor.refactoring.interfaces.IDataManagement;
import org.eclipse.emf.refactor.refactoring.runtime.ltk.LtkEmfRefactoringProcessorAdapter;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.ExpressionInOCL;
import org.eclipse.ocl.examples.pivot.LetExp;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.xtext.base.basecs.ConstraintCS;

public final class RefactoringController implements IController{

	/**
	 * Refactoring supported by the controller.
	 * @generated
	 */
	private Refactoring parent;
	
	/**
	 * DataManagement object of the model refactoring.
	 * @generated
	 */
	private RefactoringDataManagement dataManagement = 
									new RefactoringDataManagement();
	
	/**
	 * Invocation context of the model refactoring.
	 * @generated
	 */	
	private List<EObject> selection = new ArrayList<EObject>();
	
	/**
	 * Ltk RefactoringProcessor of the model refactoring.
	 * @generated
	 */
	private InternalRefactoringProcessor refactoringProcessor = null;
	
	/**
	 * Gets the Refactoring supported by the controller.
	 * @return Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#getParent()
	 * @generated
	 */
	@Override
	public Refactoring getParent() {
		return this.parent;
	}
	
	/**
	 * Sets the Refactoring supported by the controller.
	 * @param emfRefactoring Refactoring supported by the controller.
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
	 * setParent(org.eclipse.emf.refactor.refactoring.core.Refactoring)
	 * @generated
	 */
	@Override
	public void setParent(Refactoring emfRefactoring) {
		this.parent = emfRefactoring;
	}
	
	/**
	 * Returns the DataManagement object of the model refactoring.
	 * @return DataManagement object of the model refactoring.
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
	 * getDataManagementObject()
	 * @generated
	 */
	@Override
	public IDataManagement getDataManagementObject() {
		return this.dataManagement;
	}

	/**
	 * Returns the ltk RefactoringProcessor of the model refactoring.
	 * @return Ltk RefactoringProcessor of the model refactoring.
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
	 * getLtkRefactoringProcessor()
	 * @generated
	 */
	@Override
	public RefactoringProcessor getLtkRefactoringProcessor() {
		return this.refactoringProcessor;
	}
	
	/**
	 * Sets the selected EObject (invocation context of the model refactoring).
	 * @param selection Invocation context of the model refactoring.
	 * @see org.eclipse.emf.refactor.refactoring.interfaces.IController#
	 * setSelection(java.util.List)
	 * @generated
	 */
	@Override
	public void setSelection(List<EObject> selection) {
		this.selection = selection;
		this.refactoringProcessor = 
				new InternalRefactoringProcessor(this.selection);
	}	
	
	/**
	 * Returns a Runnable object that executes the model refactoring.
	 * @return Runnable object that executes the model refactoring.
	 * @generated
	 */
	private Runnable applyRefactoring() {
		return new Runnable() {				
			/**
			 * @see java.lang.Runnable#run()
			 * @generated
			 */
			@Override
			public void run() {
				org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS selectedEObject = 
					(org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String variableName =
					(String) dataManagement.getInPortByName("variableName").getValue();
				Constraint constraint = getConstraint(selectedEObject);
				PivotFactory factory = PivotFactory.eINSTANCE;
				LetExp letExp = factory.createLetExp();
				EList<EObject> allElements = getAllElements(constraint);
				// TODO: implement model transformation
			}
			
			private Constraint getConstraint(org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS selectedEObject) {
				EList<ConstraintCS> allConstraintCS = selectedEObject.getInvariants();
				for (ConstraintCS constraintCS : allConstraintCS) {
					Element elem = constraintCS.getPivot();
					System.out.println(elem);
					System.out.println(elem.eClass());
					return (Constraint) elem; 
				}
				return null;
			}
			
			private EList<EObject> getAllElements(Constraint constraint) {
				EList<EObject> list = new BasicEList<EObject>();
				TreeIterator<EObject> iter = constraint.eAllContents();
				while (iter.hasNext()) {
					list.add(iter.next());
				}
				return list;
			}
		};
	}

	/**
	 * Internal class for providing an instance of a LTK RefactoringProcessor 
	 * used for EMF model refactorings.	 
	 * @generated
	 */
	public final class InternalRefactoringProcessor extends 
									LtkEmfRefactoringProcessorAdapter {

		/**
		 * Constructor using the invocation context of the model refactoring.
		 * @param selection Invocation context of the model refactoring.
		 * @generated
		 */
		private InternalRefactoringProcessor(List<EObject> selection){
				super(getParent(), selection, applyRefactoring());				
		}
			
		/**
		 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#
	 	 * checkInitialConditions(org.eclipse.core.runtime.IProgressMonitor)
		 * @generated
		 */	
		@Override
		public RefactoringStatus checkInitialConditions(){
				RefactoringStatus result = new RefactoringStatus();
				org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS selectedEObject = 
					(org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				// TODO: implement initial check
				// no primitive literal expressions => error message
				// result.addFatalError("There is no primitive literal expression!");
				return result;
		}
		
		/**
		 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringProcessor#
	     * checkFinalConditions(org.eclipse.core.runtime.IProgressMonitor, 
	     * org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
		 * @generated
		 */	
		@Override
		public RefactoringStatus checkFinalConditions(){
				RefactoringStatus result = new RefactoringStatus();
				org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS selectedEObject = 
					(org.eclipse.ocl.examples.xtext.completeocl.completeoclcs.ClassifierContextDeclCS) dataManagement.
							getInPortByName(dataManagement.SELECTEDEOBJECT).getValue();
				String variableName =
					(String) dataManagement.getInPortByName("variableName").getValue();
				if (variableName.equals("unspecified")) {
					result.addFatalError("No variable name inserted!");
				}
				return result;
		}
		
	}

}