package edu.uw.cs.lil.amr.parser.rules.coordination;

import edu.cornell.cs.nlp.spf.ccg.categories.Category;
import edu.cornell.cs.nlp.spf.ccg.categories.ComplexCategory;
import edu.cornell.cs.nlp.spf.ccg.categories.ICategoryServices;
import edu.cornell.cs.nlp.spf.mr.lambda.LogicalExpression;
import edu.cornell.cs.nlp.spf.parser.ccg.rules.ITypeRaisingRule;
import edu.cornell.cs.nlp.spf.parser.ccg.rules.ParseRuleResult;
import edu.cornell.cs.nlp.spf.parser.ccg.rules.RuleName;
import edu.cornell.cs.nlp.spf.parser.ccg.rules.SentenceSpan;
import edu.cornell.cs.nlp.spf.parser.ccg.rules.RuleName.Direction;
import edu.cornell.cs.nlp.spf.parser.ccg.rules.primitivebinary.application.AbstractApplication;

/**
 * Coordination with type-raising and backward application.
 *
 * @author Yoav Artzi
 * @see AbstractCoordinationCXRaisedApply
 */
public class CoordinationCXRaisedApplyBackward extends
		AbstractCoordinationCXRaisedApply {

	private static final long	serialVersionUID	= 4400754126343881209L;
	private final RuleName		name;

	public CoordinationCXRaisedApplyBackward(AbstractCoordinationCXRule cxRule,
			ICategoryServices<LogicalExpression> categoryServices) {
		super(cxRule, categoryServices);
		this.name = RuleName.create(ITypeRaisingRule.RULE_LABEL
				+ RuleName.RULE_ADD + cxRule.getName().getLabel()
				+ RuleName.RULE_ADD + AbstractApplication.RULE_LABEL,
				Direction.BACKWARD);
		LOG.warn(
				"While it probably has no effect, %s is not handled in NF constraints",
				getName());
	}

	@Override
	public ParseRuleResult<LogicalExpression> apply(
			Category<LogicalExpression> left,
			Category<LogicalExpression> right, SentenceSpan span) {
		if (left instanceof ComplexCategory) {
			final Category<LogicalExpression> result = doApply(
					(ComplexCategory<LogicalExpression>) left, right);
			if (result != null) {
				return new ParseRuleResult<>(name, result);
			}
		}
		return null;
	}

	@Override
	public RuleName getName() {
		return name;
	}

}
