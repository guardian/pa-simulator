package controllers

import java.util.UUID
import play.api.mvc.Controller

class Team extends Controller with FileLoading {
  def fixtures(teamId: UUID) = simple(s"team/$teamId/fixtures")
  def results(teamId: UUID) = simple(s"team/$teamId/results")
}
