package com.stowellperformance.LandAnalysis.Control;

import com.stowellperformance.LandAnalysis.Domain.IField;
import com.stowellperformance.LandAnalysis.Domain.Point2D;

/**
 * The Surveyor interface scans over a field and builds a list of fertilePlots in a given field
 * @author Josh Stowell
 * @since November 2019
 *
 */
public interface ISurveyor {
	void surveyField(IField field) throws Exception;
	void surveyPoint(Point2D a, IField field) throws Exception;
	String getResults(IField field) throws Exception;
}
