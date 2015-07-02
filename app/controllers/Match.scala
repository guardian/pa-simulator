package controllers

import java.util.UUID
import play.api.mvc.{Action, Controller}

class Match extends Controller with FileLoading {
  def byId(matchId: UUID) = simple(s"match/$matchId/detail")
  def lineUps(matchId: UUID) = simple(s"match/$matchId/line-ups")
  def scoreCard(matchId: UUID) = loop(matchId, s"match/$matchId/scorecard")

  def nextFile(matchId: UUID) = Action {
    val state = LoopStates.get(matchId).getOrElse(LoopState(count = 0))
    val nextIndex = {
      if (exists(s"match/$matchId/scorecard", state.count + 1)) {
        state.count + 1
      } else {
        1
      }
    }

    LoopStates.put(matchId, state.copy(count = nextIndex))
    Ok(fullPath(s"match/$matchId/scorecard", nextIndex))
  }

}
