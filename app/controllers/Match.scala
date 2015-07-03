package controllers

import java.util.UUID
import play.api.mvc.{Action, Controller}

class Match extends Controller with FileLoading {

  def lineUps(matchId: UUID) = simple(s"match/$matchId/line-ups")

  private def detailPath(matchId: UUID) = s"match/$matchId/detail"
  private def scoreCardPath(matchId: UUID) = s"match/$matchId/scorecard"

  def byId(matchId: UUID) = loop(matchId, detailPath(matchId))
  def scoreCard(matchId: UUID) = loop(matchId, scoreCardPath(matchId))

  def nextFile(matchId: UUID) = Action {
    val state = LoopStates.get(matchId).getOrElse(LoopState(count = 0))
    val plusOne = state.count + 1
    val nextIndex = {
      if (exists(scoreCardPath(matchId), plusOne) && exists(detailPath(matchId), plusOne)) {
        plusOne
      } else {
        1
      }
    }

    LoopStates.put(matchId, state.copy(count = nextIndex))
    Ok(fullPath(scoreCardPath(matchId), nextIndex) + "\n" + fullPath(detailPath(matchId), nextIndex))
  }

}
