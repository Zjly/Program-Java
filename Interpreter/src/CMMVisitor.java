// Generated from E:/Coding/Java/Program/Antlr4/src/Interpreter\CMM.g4 by ANTLR 4.7.2

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CMMParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CMMVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CMMParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(CMMParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#compilationUnit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompilationUnit(CMMParser.CompilationUnitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#methodDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodDeclaration(CMMParser.MethodDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#formalParameters}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameters(CMMParser.FormalParametersContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(CMMParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CMMParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#variableDeclarationStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationStatement(CMMParser.VariableDeclarationStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(CMMParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(CMMParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(CMMParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#forControl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForControl(CMMParser.ForControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#forInit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForInit(CMMParser.ForInitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#forUpdate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForUpdate(CMMParser.ForUpdateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CMMParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CMMParser#parExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpression(CMMParser.ParExpressionContext ctx);
}