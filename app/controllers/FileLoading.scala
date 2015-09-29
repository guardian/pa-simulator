package controllers

import java.io.File
import java.util.UUID

import org.joda.time.LocalDate
import play.api.Play
import play.api.mvc.{Controller, Action}

import scala.io.Source

case class LoopState(count: Int)

object LoopStates {
  private var state = Map.empty[UUID, LoopState]
  def get(id: UUID) = state.get(id)
  def put(id: UUID, loopState: LoopState): Unit = state = state + (id -> loopState)
}

trait FileLoading extends Controller {

  def simple(file: String) = Action {
    Ok(load(file)).withHeaders("content-type" -> "application/xml")
  }

  def loop(id: UUID, file: String) = Action {
    val count = LoopStates.get(id).getOrElse(LoopState(1)).count
    Ok(load(s"$file.$count")).withHeaders("content-type" -> "application/xml")
  }

  private def load(file: String): String = Source.fromFile(fullPath(file)).mkString.replaceAll("""\[TODAY\]""", LocalDate.now.toString("yyyy-MM-dd"))

  def exists(file: String): Boolean = new File(fullPath(file)).exists

  def exists(file: String, count: Int): Boolean = exists(s"$file.$count")

  def fullPath(file: String): String = {
    val playRoot = Play.current.path.getCanonicalPath
    s"$playRoot/conf/xml/$file.xml"
  }

  def fullPath(file: String, count: Int): String = fullPath(s"$file.$count")

}
