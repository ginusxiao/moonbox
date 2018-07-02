package org.apache.spark.sql.datasys

import moonbox.core.datasys.{DataSystem, DataSystemRegister, Pushdownable}
import moonbox.core.execution.standalone.DataTable
import org.apache.spark.sql.catalyst.plans.JoinType
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

class SparkDataSystem extends DataSystem(Map()) with Pushdownable with DataSystemRegister {

	override def isSupportAll: Boolean = true

	override def fastEquals(other: DataSystem): Boolean = {
		other match {
			case spark:SparkDataSystem => true
			case _ => false
		}
	}

	override val supportedOperators: Seq[Class[_]] = Seq()
	override val supportedUDF: Seq[String] = Seq()
	override val supportedExpressions: Seq[Class[_]] = Seq()
	override val beGoodAtOperators: Seq[Class[_]] = Seq()
	override val supportedJoinTypes: Seq[JoinType] = Seq()

	override def buildScan(plan: LogicalPlan, sparkSession: SparkSession): DataFrame = {
		Dataset.ofRows(sparkSession, plan)
	}

	override def tableNames(): Seq[String] = { throw new UnsupportedOperationException("unsupport method tableNames") }

	override def tableProperties(tableName: String): Map[String, String] = { throw new UnsupportedOperationException("unsupport method tableOption") }

	override def tableName(): String = {  throw new UnsupportedOperationException("unsupport method tableName") }

	override def buildQuery(plan: LogicalPlan): DataTable = {
		throw new UnsupportedOperationException(s"unsupport call method buildQuery in spark datasystem")
	}

	override def shortName(): String = "spark"

	override def dataSource(): String = ""
}
