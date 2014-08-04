package com.linyongan.achartengine;

import java.util.Date;
import java.util.List;

import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.MultipleCategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

public abstract class AbstractDemoChart implements IDemoChart {

	/**
	 * ���� XYMultipleSeriesDataset.
	 * 
	 * @param titles
	 *            ÿ�����е�ͼ��
	 * @param xValues
	 *            X�������
	 * @param yValues
	 *            Y�������
	 * @return XYMultipleSeriesDataset
	 */
	protected XYMultipleSeriesDataset buildDataset(String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		addXYSeries(dataset, titles, xValues, yValues, 0);
		return dataset;
	}

	// ��DataSet���������.
	public void addXYSeries(XYMultipleSeriesDataset dataset, String[] titles,
			List<double[]> xValues, List<double[]> yValues, int scale) {
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			XYSeries series = new XYSeries(titles[i], scale); // ����ע����TimeSeries����.
			double[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
	}

	/**
	 * ����XYMultipleSeriesRenderer.
	 * 
	 * @param colors
	 *            ÿ�����е���ɫ
	 * @param styles
	 *            ÿ�����е������(����������,Բ��,����,����ȶ���)
	 * @return XYMultipleSeriesRenderer
	 */
	protected XYMultipleSeriesRenderer buildRenderer(int[] colors,
			PointStyle[] styles) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		setRenderer(renderer, colors, styles);
		return renderer;
	}

	protected void setRenderer(XYMultipleSeriesRenderer renderer, int[] colors,
			PointStyle[] styles) {
		// ����ͼ����������
		// -->start
		renderer.setAxisTitleTextSize(14);// ������������ֵĴ�С
		renderer.setChartTitleTextSize(16);// ��������ͼ��������ֵĴ�С
		renderer.setLabelsTextSize(14);// ������̶����ֵĴ�С
		renderer.setLegendTextSize(14);// ����ͼ�����ִ�С
		renderer.setPointSize(5f);// ���õ�Ĵ�С(ͼ����ʾ�ĵ�Ĵ�С��ͼ���е�Ĵ�С���ᱻ����)
		renderer.setMargins(new int[] { 20, 30, 15, 20 });// ����ͼ�����߿�(��/��/��/��)
		// -->end

		// ���´�������û�����е���ɫ.
		// -->start
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			XYSeriesRenderer r = new XYSeriesRenderer();
			r.setColor(colors[i]);// ������ɫ
			r.setPointStyle(styles[i]);
			renderer.addSeriesRenderer(r);
		}
		// -->end
	}

	/**
	 * ����renderer��һЩ����.
	 * 
	 * @param renderer
	 *            Ҫ���õ�renderer
	 * @param title
	 *            ͼ�����
	 * @param xTitle
	 *            X�����
	 * @param yTitle
	 *            Y�����
	 * @param xMin
	 *            X����Сֵ
	 * @param xMax
	 *            X�����ֵ
	 * @param yMin
	 *            Y����Сֵ
	 * @param yMax
	 *            Y�����ֵ
	 * @param axesColor
	 *            X����ɫ
	 * @param labelsColor
	 *            Y����ɫ
	 */
	protected void setChartSettings(XYMultipleSeriesRenderer renderer,
			String title, String xTitle, String yTitle, double xMin,
			double xMax, double yMin, double yMax, int axesColor,
			int labelsColor) {
		renderer.setChartTitle(title);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
	}

	/**
	 * ������ʱ���йص�XYMultipleSeriesDataset,���������buildDataset�ڲ�������������ҪList<Date[]>������
	 * .
	 * 
	 * @param titles
	 *            ����ͼ��
	 * @param xValues
	 *            X��ֵ
	 * @param yValues
	 *            Y��ֵ
	 * @return XYMultipleSeriesDataset
	 */
	protected XYMultipleSeriesDataset buildDateDataset(String[] titles,
			List<Date[]> xValues, List<double[]> yValues) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			TimeSeries series = new TimeSeries(titles[i]);// ����ʱ������TimeSeries,
			Date[] xV = xValues.get(i);
			double[] yV = yValues.get(i);
			int seriesLength = xV.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(xV[k], yV[k]);
			}
			dataset.addSeries(series);
		}
		return dataset;
	}

	/**
	 * ��������CategorySeries,���������ɱ�ͼ,ע����buildMultipleCategoryDataset(����Բ��ͼ)������.
	 * 
	 * @param titles
	 *            the series titles
	 * @param values
	 *            the values
	 * @return the category series
	 */
	protected CategorySeries buildCategoryDataset(String title, double[] values) {
		CategorySeries series = new CategorySeries(title);
		int k = 0;
		for (double value : values) {
			series.add("Project " + ++k, value);
		}

		return series;
	}

	/**
	 * ����MultipleCategorySeries,�����ڹ���Բ��ͼ(ÿ������һ������)
	 * 
	 * @param titles
	 *            the series titles
	 * @param values
	 *            the values
	 * @return the category series
	 */
	protected MultipleCategorySeries buildMultipleCategoryDataset(String title,
			List<String[]> titles, List<double[]> values) {
		MultipleCategorySeries series = new MultipleCategorySeries(title);
		int k = 0;
		for (double[] value : values) {
			series.add(2007 + k + "", titles.get(k), value);
			k++;
		}
		return series;
	}

	/**
	 * ����DefaultRenderer.
	 * 
	 * @param colors
	 *            ÿ�����е���ɫ
	 * @return DefaultRenderer
	 */
	protected DefaultRenderer buildCategoryRenderer(int[] colors) {
		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		renderer.setMargins(new int[] { 20, 30, 15, 10 });
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

	/**
	 * ����XYMultipleSeriesDataset,��������״ͼ.
	 * 
	 * @param titles
	 *            ÿ���������е�ͼ��
	 * @param values
	 *            ���ӵĸ߶�ֵ
	 * @return XYMultipleSeriesDataset
	 */
	protected XYMultipleSeriesDataset buildBarDataset(String[] titles,
			List<double[]> values) {
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		int length = titles.length;
		for (int i = 0; i < length; i++) {
			CategorySeries series = new CategorySeries(titles[i]);
			double[] v = values.get(i);
			int seriesLength = v.length;
			for (int k = 0; k < seriesLength; k++) {
				series.add(v[k]);
			}
			dataset.addSeries(series.toXYSeries());
		}
		return dataset;
	}

	/**
	 * ����XYMultipleSeriesRenderer,��������״ͼ.
	 * 
	 * @param colors
	 *            ÿ�����е���ɫ
	 * @return XYMultipleSeriesRenderer
	 */
	protected XYMultipleSeriesRenderer buildBarRenderer(int[] colors) {
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setAxisTitleTextSize(16);
		renderer.setChartTitleTextSize(20);
		 //����̶������С
		renderer.setLabelsTextSize(15);
		renderer.setLegendTextSize(15);
		int length = colors.length;
		for (int i = 0; i < length; i++) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(colors[i]);
			renderer.addSeriesRenderer(r);
		}
		return renderer;
	}

}
