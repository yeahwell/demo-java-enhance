package com.yeahwell.demo.kilim;

import java.math.BigDecimal;

public class Calculation {
	
	private BigDecimal dividend;
	private BigDecimal divisor;
	private BigDecimal answer;

	public Calculation(BigDecimal dividend, BigDecimal divisor) {
		super();
		this.dividend = dividend;
		this.divisor = divisor;
	}

	public BigDecimal getDividend() {
		return dividend;
	}

	public BigDecimal getDivisor() {
		return divisor;
	}

	public void setAnswer(BigDecimal ans) {
		this.answer = ans;
	}

	public BigDecimal getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Calculation [dividend=").append(dividend)
				.append(", divisor=").append(divisor).append(", answer=")
				.append(answer).append(", toString()=")
				.append(super.toString()).append("]");
		return builder.toString();
	}
	
	
}